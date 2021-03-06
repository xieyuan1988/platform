package com.taobao.tddl.rule.ruleengine.entities.abstractentities;


/**
 * 主要提供了一些公共的方法
 * 
 * 根节点抽象
 * 
 * @author shenxun
 * 
 */
public abstract class SharedElement implements Cloneable,OneToMany {

	private String id;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getId() {
		return id;
	}

	public void init() {
	}
	/**
	 * 兼容性方法，因为以前TableRule暴露了init方法给外部使用。
	 * @param invokeBySpring
	 */
	public void init(boolean invokeBySpring){
		
	}

	/**
	 * 如果用户通过map的方式设置子节点，则在init的过程中会将map的key作为子节点的id设置进来。
	 * 如果用户采用list的方式设置子节点，则list的下标的string会成为子节点的id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	public void put(OneToManyEntry oneToManyEntry) {
		//do nothing 
		throw new IllegalArgumentException("should not be here");
	}
}
