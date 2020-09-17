package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<BoardBean> articleList=new ArrayList<BoardBean>();
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null)
		{
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		BoardListService boardListService=new BoardListService();
		int listcount=boardListService.getListCount();
		articleList=boardListService.getArticleList(page,limit);
		int maxPage=(int) ((double)listcount/limit+0.95);
		int startPage=(((int)((double)page/10+0.9))-1)*10+1;
		int endPage=startPage+10-1;
		
		if(endPage>maxPage) endPage=maxPage;
		
		PageInfo pageInfo=new PageInfo();
		pageInfo.setendpage(endPage);
		pageInfo.setlistcount(listcount);
		pageInfo.setmaxPage(maxPage);
		pageInfo.setpage(page);
		pageInfo.setstartpage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward=new ActionForward();
		forward.setPath("/board/qna_board_list.jsp");
		return forward;
	}
}
