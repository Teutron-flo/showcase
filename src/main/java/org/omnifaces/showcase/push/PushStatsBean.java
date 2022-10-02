package org.omnifaces.showcase.push;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.Startup;
import org.omnifaces.showcase.PageView;

@Named
@Startup
public class PushStatsBean {

	private static final int MAX_LAST_PAGE_VIEWS = 20;
	private Deque<PageView> lastPageViews;

	@Inject @Push
	private PushContext stats;

	@PostConstruct
	public void init() {
		lastPageViews = new LinkedBlockingDeque<>(MAX_LAST_PAGE_VIEWS);
	}

	public void onPageView(@Observes PageView pageView) {
		while (!lastPageViews.offerFirst(pageView)) {
			lastPageViews.pollLast();
		}

		stats.send(pageView);
	}

	public int getMaxLastPageViews() {
		return MAX_LAST_PAGE_VIEWS;
	}

	@Produces @Model
	public List<PageView> getLastPageViews() {
		return new ArrayList<>(lastPageViews);
	}

}