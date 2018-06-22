package com.neusoft.duanmlssh.platform.core.db.interImpl;

import org.apache.commons.lang.math.NumberUtils;

import com.neusoft.duanmlssh.platform.core.db.inter.PaginationInter;

/**
 * 
*
* <p>Title: 河源人社局</p>
*
* <p>Description: 简单分页的代码实现类</p>
*
* <p>Copyright: Copyright (c) 2014</p>
*
* <p>Company: neusoft</p>
*
* @author 段美林
*
* @date  2014-8-30
*
* @version 1.0
 */
public class SimplePage implements PaginationInter {
	
	public static final int DEF_PageSize = 20;
	
	protected int totalCount = 0;
	protected int pageSize = 20;
	protected int pageNo = 1;
	
	public SimplePage(){}
	
	public SimplePage(int pageNo,int pageSize,int totalCount){
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}
	
	/**
	 * 
	* <p>@Description:检查页码 checkPageNo</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public static int cpn(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}

	public static int cpn(String pageNo) {
		return (!NumberUtils.isNumber(pageNo) || Integer.parseInt(pageNo)<1) ? 1 : Integer.parseInt(pageNo);
	}
	
	
	/**
	 * 
	* <p>@Description:检查每页显示数据条数 </p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public static int isCheckPageSize(Integer numPerPage) {
		return (numPerPage == null || numPerPage < 5) ? 5 : numPerPage;
	}

	public static int isCheckPageSize(String numPerPage) {
		return (!NumberUtils.isNumber(numPerPage) || Integer.parseInt(numPerPage) < 5) ? 5 : Integer.parseInt(numPerPage);
	}
	
	
	/**
	 * 
	* <p>@Description:调整页码，使之不超过最大页数</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}
	
	/**
	 * 
	* <p>@Description:设定数据的总共条数参数</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	/**
	 * 
	* <p>@Description:设定分页的数据大小</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_PageSize;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 
	* <p>@Description:设定分页的页号</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	@Override
	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public int getTotalPage() {
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getPageNo() {
		return pageNo;
	}

	@Override
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	@Override
	public boolean isLastPage() {
		return pageNo >= this.getTotalPage();
	}

	@Override
	public int getNextPage() {
		if (this.isLastPage()) {
			return pageNo;
		}else{
			return pageNo + 1;
		}
	}

	@Override
	public int getPrePage() {
		if (this.isFirstPage()) {
			return pageNo;
		}else{
			return pageNo - 1;
		}
	}
	
	

}
