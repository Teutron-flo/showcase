package org.omnifaces.showcase.components;

import static org.omnifaces.util.Faces.getContext;
import static org.omnifaces.util.Messages.addGlobalInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.component.output.cache.CacheFactory;

@RequestScoped
@ManagedBean
public class CacheBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> items;
	private long key;
	private String item;
	private boolean reset;

	@PostConstruct
	public void init() {
		String date = new Date().toString();
		items = new ArrayList<String>();
		items.add("A - " + date);
		items.add("B - " + date);
		items.add("C - " + date);
	}
	
	/**
	 * Demos reset via programmatic method.
	 */
	public void resetProgrammatic() {
		CacheFactory.getCache(getContext(), "session").remove("firstCache");
	}
	
	/**
	 * Alternative way to reset via binding property to "reset" attribute of
	 * cache component.
	 */
	public void resetAttribute() {
		// Note, no handler to revert back to false needed since bean
		// is request scope.
		this.reset = true;
	}
	
	public void saveItem(String item) {
		this.item = item;
		addGlobalInfo("Item {0} was posted back", item);
	}
	
	public List<String> getItems() {
		return items;
	}
	
	public String getItem() {
		return item;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public boolean isReset() {
		return reset;
	}

}
