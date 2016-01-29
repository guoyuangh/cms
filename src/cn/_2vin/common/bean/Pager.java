package cn._2vin.common.bean;
/**
 * 分页实体类
 * @author liuxuewen
 * @date 2014-1-22
 */
public class Pager {
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	public static final Integer DEFAULT_CURRENT_PAGE = 1;

	private Integer fromNumber = 0;/*数据库选择范围的开始数*/
	private Integer totalSize = 0;
	private Integer maxSize = DEFAULT_PAGE_SIZE;
	private Integer currentPage = DEFAULT_CURRENT_PAGE;
	private Integer totalPage = 0;

	public Integer getFromNumber() {
		fromNumber = (currentPage - 1) * maxSize;
		return fromNumber;
	}

	public void setFromNumber(Integer fromNumber) {
		this.fromNumber = fromNumber;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		totalPage = (totalSize % maxSize) == 0 ? (totalSize / maxSize) : (totalSize / maxSize) + 1;
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
