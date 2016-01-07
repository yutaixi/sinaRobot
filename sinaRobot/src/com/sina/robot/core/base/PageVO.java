package com.sina.robot.core.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PageVO  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1159062567251318177L;

	private long curPage;
	
	private long pageSize=20;
	
	private long totalRows;

	private long startIndex;
	
	private long endIndex;

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public long getStartIndex() {
		return ((this.startIndex==0) ? (this.curPage-1)*this.pageSize+1:this.startIndex);
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public long getEndIndex() {
//		return ((this.endIndex>this.totalRows)? this.totalRows : (this.endIndex==0)? this.curPage*this.pageSize : this.endIndex);
		return  ((this.endIndex==0)? this.curPage*this.pageSize : this.endIndex);
	}

	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}
	
	
	
	
	

}
