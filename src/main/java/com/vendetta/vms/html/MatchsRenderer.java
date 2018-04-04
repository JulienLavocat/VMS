package com.vendetta.vms.html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.vendetta.vms.Match;
import com.vendetta.vms.Matchs;

public class MatchsRenderer {
	
	public synchronized static String render() {
		
		String html = "";
		try {
			html = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/matchs.html")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String td = "<td>$data</td>\n";
		String s = "";
		
		int i = 0;
		for(Match m : Matchs.matchs) {
			s+= "<tr>\n";
			s+= td.replace("$data", ""+i);
			s+= td.replace("$data", m.ip);
			s+= td.replace("$data", m.key);
			s+= td.replace("$data", ""+m.port);
			s+= td.replace("$data", ""+m.createdAt);
			s+= td.replace("$data", ""+m.players);
			s+= "</tr>\n";
			i++;
		}

		return html.replace("$values", s).replace("$matchCount", ""+Matchs.matchs.size());
	}
	
}
