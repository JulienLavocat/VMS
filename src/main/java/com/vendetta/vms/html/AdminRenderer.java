package com.vendetta.vms.html;

import static j2html.TagCreator.*;

public class AdminRenderer {

	public static String render() {
		
		return html(
				
				head(
						title("Vendetta MS | Admin")
					),
				body(
						a("Match list").withHref("/admin/matchs")
					)
				
				).render();
		
	}

}
