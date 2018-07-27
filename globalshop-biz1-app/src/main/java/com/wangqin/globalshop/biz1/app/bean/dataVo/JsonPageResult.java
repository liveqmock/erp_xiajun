package com.wangqin.globalshop.biz1.app.bean.dataVo;


/**
 * json分页结果封装
 *
 */
public class JsonPageResult<T> extends JsonResult<T> {

	private static final long serialVersionUID = -6468515082941696950L;

	private int pageIndex = 1;

	private int pageSize;
	
	private int totalCount;
	
	private int totalPage;
	
	private boolean agentRoler = false;
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	// 增加链式调用方法
	public JsonPageResult<T> buildPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}

	public JsonPageResult<T> buildPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public JsonPageResult<T> buildTotalCount(int totalCount) {
		this.totalCount = totalCount;
		return this;
	}
	public JsonPageResult<T> buildTotalPage() {
		if(this.pageSize> 0 && this.totalCount >0) {
			this.totalPage =  this.totalCount % this.pageSize == 0 ? (this.totalCount / this.pageSize) : ( this.totalCount / this.pageSize +1 );
		} else {
			this.totalPage = 0;
		}
		return this;
	}


	public JsonPageResult<T> buildTotalPage(int totalPage) {
		this.totalPage = totalPage;
		return this;
	}

	// 重写父类方法
	@Override
    public JsonPageResult<T> buildData(T data) {
		this.data = data;
		return this;
	}
	@Override
    public JsonPageResult<T> buildIsSuccess(boolean success) {
		this.success = success;
		return this;
	}
	@Override
    public JsonPageResult<T> buildMsg(String msg) {
		this.msg = msg;
		return this;
	}

	// 通用方法，用来build基本的返回对象
	public static <T> JsonPageResult<T> buildSuccess(T data, int totalCount, int totalPage, int pageIndex, String msg){
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildData(data).buildMsg(msg).buildTotalCount(totalCount).buildIsSuccess(true).buildPageIndex(pageIndex).buildTotalPage(totalPage);
	}
/*	public static <T> JsonPageResult<T> buildSuccess(T data, int totalCount, int totalPage, int pageIndex){
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildData(data).buildMsg(CommonConstant.DEFAULT_EMPTY).buildTotalCount(totalCount).buildIsSuccess(true).buildPageIndex(pageIndex).buildTotalPage(totalPage);
	}
	public static <T> JsonPageResult<T> buildSuccess(T data, int totalCount, int totalPage){
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildData(data).buildMsg(CommonConstant.DEFAULT_EMPTY).buildTotalCount(totalCount).buildIsSuccess(true).buildPageIndex(0).buildTotalPage(totalPage);
	}
	public static <T> JsonPageResult<T> buildSuccess(T data, int totalCount){
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildData(data).buildMsg(CommonConstant.DEFAULT_EMPTY).buildTotalCount(totalCount).buildIsSuccess(true).buildPageIndex(0).buildTotalPage(0);
	}
	public static <T> JsonPageResult<T> buildSuccess(T data){
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildData(data).buildMsg(CommonConstant.DEFAULT_EMPTY).buildTotalCount(0).buildIsSuccess(true).buildPageIndex(0).buildTotalPage(0);
	}*/

	public static <T> JsonPageResult<T> buildError(String msg) {
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildIsSuccess(false).buildMsg(msg);
	}
	public static <T> JsonPageResult<T> SystemError() {
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildIsSuccess(false).buildMsg("系统异常");
	}
	public static <T> JsonPageResult<T> buildParamError() {
		JsonPageResult<T> result = new JsonPageResult<T>();
		return result.buildIsSuccess(false).buildMsg("参数异常");
	}
	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置.
	 */
	public Integer getFirstStart() {
		return (pageIndex > 0 && pageSize > 0) ? ((pageIndex - 1) * pageSize + 0) : 0;
	}
	
	/**
	 * 初始化分析查询和返回信息
	 * @param totalPage
	 * @param pageQueryVO
	 * @return
	 */
	public JsonPageResult<T> buildPage(int totalPage,PageQueryVO pageQueryVO) {
		this.setTotalCount(totalPage);
		this.setPageIndex(pageQueryVO.getPageIndex());
		this.buildPageSize(pageQueryVO.getPageSize()).buildTotalPage();
		pageQueryVO.setFirstStart(this.getFirstStart());
		return this;
	}

	public boolean isAgentRoler() {
		return agentRoler;
	}

	public void setAgentRoler(boolean agentRoler) {
		this.agentRoler = agentRoler;
	}
	

}
