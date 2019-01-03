package com.xhSmat.util;

import java.io.Serializable;

/**
 * 分页的类
 * @author lin
 *
 */
public class Page implements Serializable {

	private static final long serialVersionUID = -3198048449643774660L;

	private int pageNow = 1; // 当前页数

	private int pageSize = 12; // 每页显示记录的条数

	private int totalCount; // 总的记录条数

	private int totalPageCount; // 总的页数

	@SuppressWarnings("unused")
	private int startPos; // 开始位置，从0开始

	@SuppressWarnings("unused")
	private boolean hasFirst;// 是否有首页

	@SuppressWarnings("unused")
	private boolean hasPre;// 是否有前一页

	@SuppressWarnings("unused")
	private boolean hasNext;// 是否有下一页

	@SuppressWarnings("unused")
	private boolean hasLast;// 是否有最后一页
	
	/**
	 * 通过构造函数 传入  总记录数  和  当前页
	 * @param totalCount
	 * @param pageNow
	 */
	public Page(int totalCount, int pageNow) {
		this.totalCount = totalCount;
		this.pageNow = pageNow;
	}
	
	/**
	 * 取得总页数，总页数=总记录数/总页数
	 * @return 总页数
	 */
	public int getTotalPageCount() {
		totalPageCount = getTotalCount() / getPageSize();
		return (totalCount % pageSize == 0) ? totalPageCount
				: totalPageCount + 1;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 取得选择记录的初始位置
	 * @return 录的初始位置
	 */
	public int getStartPos() {
		return (pageNow - 1) * pageSize;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	/**
	 * 是否是第一页
	 * @return false-是第一页，true-不是第一页
	 */
	public boolean isHasFirst() {
		return (pageNow == 1) ? false : true;
	}

	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	/**
	 * 是否有首页
	 * @return true-有首页，false-没有首页
	 */
	public boolean isHasPre() {
		// 如果有首页就有前一页，因为有首页就不是第一页
		return isHasFirst() ? true : false;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	/**
	 * 是否有下一页
	 * @return true-有下一页，false-没有下一页
	 */
	public boolean isHasNext() {
		// 如果有尾页就有下一页，因为有尾页表明不是最后一页
		return isHasLast() ? true : false;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	/**
	 * 是否有尾页
	 * @return false-没有尾页，true-有尾页
	 */
	public boolean isHasLast() {
		// 如果不是最后一页就有尾页
		return (pageNow == getTotalCount()) ? false : true;
	}

	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

}