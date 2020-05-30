package table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.railbot.project.actions.IAction;
import com.railbot.project.models.Request;
import com.railbot.project.models.Response;
import com.railbot.project.models.Response_Builder;

public class Train_route1 implements IAction {
	String station_list="";
	String[]  header_array={"Station","Arrival","Departure"};
	@Override
	public Response performAction(Request request) throws ClassNotFoundException, SQLException {
		Response response = new Response();
		Response_Builder rb = new Response_Builder();
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		Map<String, Object> parameters = request.getQueryResult().getParameters();
		String train_number = parameters.get("train_no").toString(); 
		String res="";
		try {
			Document doc = Jsoup.connect("https://erail.in/train-enquiry/"+train_number)
					// and other hidden fields which are being passed in post request.
					.userAgent("Mozilla")
					.post();
			Elements d=doc.select("table.DataTable");
			Elements d2=d.select("tr");
			for(int i=1;i<d2.size();i++){
				Element temp=d.select("tr").get(i);
				res = res+temp.select("td").get(1).text()+"\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		output=rb.String_response(res);
		response.setFulfillmentText(res);
		response.setFulfillmentMessages(output);
		return response;
	
	}
	
}
