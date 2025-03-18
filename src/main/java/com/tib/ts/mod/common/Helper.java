package com.tib.ts.mod.common;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.tib.ts.mod.entities.HydraView;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Service
public class Helper {

	public HydraView getView(String baseUrl, JsonObject responseObject) {

		int totalPages = responseObject.get("totalPages").getAsInt();
		int currentPage = responseObject.get("page").getAsInt();

		HydraView view = new HydraView();
		view.setId(formatUrl(baseUrl, currentPage));
		view.setFirst(formatUrl(baseUrl, 1));
		view.setLast(formatUrl(baseUrl, totalPages));

		if (totalPages > (currentPage + 1))
			view.setNext(formatUrl(baseUrl, currentPage + 1));

		if (currentPage >= 1)
			view.setPrevious(formatUrl(baseUrl, currentPage - 1));

		return view;
	}

	private String formatUrl(String baseUrl, int page) {
		return "%s?page=%d".formatted(baseUrl, page);
	}

}
