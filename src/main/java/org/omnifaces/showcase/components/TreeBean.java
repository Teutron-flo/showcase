package org.omnifaces.showcase.components;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.model.tree.ListTreeModel;
import org.omnifaces.model.tree.TreeModel;
import org.omnifaces.showcase.model.ExampleEntity;
import org.omnifaces.util.Ajax;

@Named
@ViewScoped
public class TreeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeModel<ExampleEntity> tree;

	@PostConstruct
	public void init() {
		tree = new ListTreeModel<>();
		tree.addChild(new ExampleEntity(1L, "One"))
				.addChild(new ExampleEntity(2L, "Two")).getParent()
				.addChild(new ExampleEntity(3L, "Three")).getParent()
				.getParent()
			.addChild(new ExampleEntity(4L, "Four"))
				.addChild(new ExampleEntity(5L, "Five"));
	}

	public void addChild(TreeModel<ExampleEntity> node) {
		node.addChild(new ExampleEntity());
	}

	public void remove(TreeModel<ExampleEntity> node) {
		node.remove();
	}

	public void save() {
		Ajax.update("staticTree");
	}

	public TreeModel<ExampleEntity> getTree() {
		return tree;
	}

}