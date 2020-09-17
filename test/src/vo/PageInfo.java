package vo;

public class PageInfo
{
	private int page;
	private int maxPage;
	private int startpage;
	private int endpage;
	private int listcount;
	
	public int getpage()
	{
		return page;
	}
	public void setpage(int page)
	{
		this.page=page;
	}
	
	public int getmaxPage()
	{
		return maxPage;
	}
	public void setmaxPage(int maxPage)
	{
		this.maxPage=maxPage;
	}
	public int getstartpage()
	{
		return startpage;
	}
	public void setstartpage(int startpage)
	{
		this.startpage=startpage;
	}
	public int getendpage()
	{
		return endpage;
	}
	public void setendpage(int endpage)
	{
		this.endpage=endpage;
	}
	public int getlistcount()
	{
		return listcount;
	}
	public void setlistcount(int listcount)
	{
		this.listcount=listcount;
	}
	
}
