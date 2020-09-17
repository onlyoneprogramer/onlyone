package action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;
import com.oreilly.servlet.ultipartRequest;
import com.oreilly.servlet.multpart.DefaultFilleRenamePolicy;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward=null;
		BoardBean boardBean=null;
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=5*1024*1024;
		ServletContext context=request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartReqeust multi=new MultipartReqeust(request,realFolder,fileSize,
				"UTF-8",new DefaultFilleRenamePolicy());
		boardBean=new BoardBean();
		boardBean.setBoard_name(multi.getParameter("Board_name"));
		boardBean.setBoard_pass(multi.getParameter("Board_pass"));
		boardBean.setBoard_subject(multi.getParameter("Board_subject"));
		boardBean.setBoard_content(multi.getParameter("Board_content"));
		boardBean.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		BoardWriteProService boardWriteProService = new BoardWriteProService();
		boolean isWriteSuccess=boardWriteProService.registArticle(boardBean);
		
		if(!isWriteSuccess)
		{
			response.setContentType("text.html;charset=UTF-8");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.bo");
		}
		
		return forward;
	}
}