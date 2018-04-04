package com.vendetta.vms;

import java.util.UUID;

import io.javalin.Javalin;
import com.vendetta.vms.html.AdminRenderer;
import com.vendetta.vms.html.MatchsRenderer;

public class Router {

	public static final int APP_PORT = 8080;

	public Router() {

		Javalin app = Javalin.start(APP_PORT);

		app.get("/user/auth", ctx -> {

			String name = ctx.queryParam("name");
			if(name == null || name.equals("")) {
				ctx.json(new Error("Invalid name"));
				return;
			}
			User u = DB.createUser(new User(name));
			ctx.json(u);

		});

		app.get("/user/get", ctx -> {

			String id = ctx.queryParam("id");
			if(id == null || id.equals("")) {
				ctx.json(new Error("Invalid id"));
				return;
			}
			
			try {
				UUID uid = UUID.fromString(id);
				ctx.json(User.getUser(uid));
			} catch(Exception e) {
				e.printStackTrace();
				ctx.json(new Error("Invalid id"));
			}

		});

		app.get("/user/match", ctx -> {

			ctx.json(Matchs.getMatch(UUID.randomUUID()));

		});

		app.get("/gs", ctx -> {
			
			
			
		});
		
		app.get("/admin", ctx -> {
			ctx.html(AdminRenderer.render());
		});

		app.get("/admin/:resource", ctx -> {

			switch (ctx.param("resource")) {
			case "matchs":
				ctx.html(MatchsRenderer.render());
				break;

			default:
				ctx.html(AdminRenderer.render());
				break;
			}

		});

	}

}
