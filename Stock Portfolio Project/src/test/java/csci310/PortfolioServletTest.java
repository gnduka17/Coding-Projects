/**
 * 
 */
package csci310;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import csci310.servlets.PortfolioServlet;

/**
 * @author zhangchaohang
 *
 */
public class PortfolioServletTest extends Mockito {
	
	@Mock
	HttpSession session;

//	@Test
//	public void testDoGet() throws IOException {
////		HttpServletRequest request = mock(HttpServletRequest.class);
////		HttpServletResponse response = mock(HttpServletResponse.class);
////		
////		when(request.getParameter("firstname")).thenReturn("Portfolio");
////		
////		
////		StringWriter stringWriter = new StringWriter();
////        PrintWriter writer = new PrintWriter(stringWriter);
////        when(response.getWriter()).thenReturn(writer);
////
////        PortfolioServlet ps = new PortfolioServlet();
////		
////		try {
////			ps.doGet(request, response);
////		} catch (ServletException e) {
////			e.printStackTrace();
////			System.out.println("fail");
////		}
////		writer.flush();
////		assertTrue(stringWriter.toString().contains("Portfolio"));
//		
//		HttpServletRequest request = mock(HttpServletRequest.class);
//		HttpServletResponse response = mock(HttpServletResponse.class);
//		session = mock(HttpSession.class);
//		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println(dtf.format(now));
//		String year = dtf.format(now).substring(0,4);
//		String month = dtf.format(now).substring(5,7);
//		String day = dtf.format(now).substring(8,10);
//		int lastYear = Integer.parseInt(year) - 1;
//		String startYear = Integer.toString(lastYear);
//		String startDate = startYear + "-" + month + "-" + day;
//		String endDate = year + "-" + month + "-" + day;
//		
//		List<Double> intraday_open_price = Stock_api.stock_price_with_timerange("AAPL", startDate,endDate);
//		String curr_price = intraday_open_price.get(0).toString();
//		
//		when(request.getParameter("stockCode")).thenReturn("AAPL");
//		HttpSession session = mock(HttpSession.class);
//		when(request.getSession()).thenReturn(session);
//		when(request.getSession().getAttribute("username")).thenReturn("admin");
////		when(session.getAttribute("username")).thenReturn("Ada");
//		
//		StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        PortfolioServlet ps = new PortfolioServlet();
//		
//		try {
//			ps.doGet(request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//			System.out.println("fail");
//		}
//		writer.flush();
//		System.out.print(stringWriter.toString());
//		System.out.print(curr_price);
//		assertTrue(stringWriter.toString().contains(curr_price));
//	}
	
//	@Test
//	public void testInvalidStockCode() throws IOException {
//		
//		HttpServletRequest request = mock(HttpServletRequest.class);
//		HttpServletResponse response = mock(HttpServletResponse.class);
//		session = mock(HttpSession.class);
//
//		
//		when(request.getParameter("stockCode")).thenReturn("APPL");
//		HttpSession session = mock(HttpSession.class);
//		when(request.getSession()).thenReturn(session);
//		when(request.getSession().getAttribute("username")).thenReturn("admin");
////		when(session.getAttribute("username")).thenReturn("Ada");
//		
//		StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        PortfolioServlet ps = new PortfolioServlet();
//		
//		try {
//			ps.doGet(request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//			System.out.println("fail");
//		}
//		writer.flush();
//		System.out.print(stringWriter.toString());
//		assertTrue(stringWriter.toString().contains("Please enter the right stock code"));
//	}
	
	@Test
	public void testAddStock() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("IBM");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockCode")).thenReturn("IBM");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		when(request.getParameter("sellDate")).thenReturn("2020-10-29");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("success"));
	}
	
	@Test
	public void testNoBuyinDay() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("LOW");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockCode")).thenReturn("LOW");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("");
//		when(request.getParameter("sellDate")).thenReturn("2020-10-29");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Please enter the purchase date."));
	}
	@Test
	public void testBuyTooEarly() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("LOW");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockCode")).thenReturn("LOW");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2015-10-29");
//		when(request.getParameter("sellDate")).thenReturn("2020-10-29");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Buy in date must be within the last year!"));
	}
	@Test
	public void testSellTooLate() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("IBM");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockCode")).thenReturn("IBM");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		when(request.getParameter("sellDate")).thenReturn("2019-10-29");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Sell date must after buy in date!"));
	}
	@Test
	public void testAddWrongStock() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("aks");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockCode")).thenReturn("adcadc");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		when(request.getParameter("sellDate")).thenReturn("2020-10-29");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Please enter the right stock code"));
	}

	@Test
	public void testAddStockAlreadyExist() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
//		when(request.getParameter("stockCode")).thenReturn("AAPL");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockCode")).thenReturn("AAPL");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Stock already exist."));
	}

	@Test
	public void testAddStockNegativeShares() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
//		when(request.getParameter("stockCode")).thenReturn("AAPL");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockCode")).thenReturn("IBM");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("-10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Please enter the number of shares to buy."));
	}
	
	@Test
	public void testAddStockGhostuser() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("Ghost");
		when(request.getParameter("stockCode")).thenReturn("IBM");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("fail"));
	}

	@Test
	public void testDeleteStock() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("PortofolioTestUser");
		when(request.getParameter("stockToDelete")).thenReturn("IBM");
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("success"));
	}
	
	
	
	@Test
	public void testAddtoWatchlist() throws IOException {
		
//		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
//		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//		FirebaseOptions options = new FirebaseOptions.Builder()
//		    .setCredentials(credentials)
//		    .build();
//		if(FirebaseApp.getApps().isEmpty()) { 
//            FirebaseApp.initializeApp(options);
//        }
//		
//		Firestore db2 = FirestoreClient.getFirestore();
//		
//		Map<String, List<Double>> tempStock = db2.collection("users").document("addwatch").get("stocks");
//
//		
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("IBM");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("addwatch");
		when(request.getParameter("watchlist")).thenReturn("yes");
		when(request.getParameter("stockCode")).thenReturn("IBM");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		when(request.getParameter("sellDate")).thenReturn("2021-10-29");
		
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("success"));
//		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("addwatch").delete();
		
		
	}
	
	
	@Test
	public void testAddtoWatchlistExist() throws IOException {

		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("IBM");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("addwatch");
		when(request.getParameter("watchlist")).thenReturn("yes");
		when(request.getParameter("stockCode")).thenReturn("AAPL");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		when(request.getParameter("sellDate")).thenReturn("2021-10-29");
		
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Stock already exist."));
//		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("addwatch").delete();
		
		
	}
	@Test
	public void testDeleteWatchlist() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("addwatch");
		when(request.getParameter("stockToDelete")).thenReturn("IBM");
		when(request.getParameter("watchlist")).thenReturn("yes");
		when(request.getParameter("watchListDelete")).thenReturn("yes");
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("success"));
	}
	
	
	@Test
	public void testAddtoWatchlistLateSell() throws IOException {

		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("IBM");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("addwatch");
		when(request.getParameter("stockCode")).thenReturn("MSFT");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2020-10-29");
		when(request.getParameter("sellDate")).thenReturn("2019-10-29");
		when(request.getParameter("watchlist")).thenReturn("yes");
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Sell date must after buy in date!"));
//		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("addwatch").delete();
		
		
	}
	
	@Test
	public void testAddtoWatchlistWrongShares() throws IOException {

		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("IBM");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("addwatch");
		when(request.getParameter("stockCode")).thenReturn("MSFT");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("-8");
		when(request.getParameter("buyInDate")).thenReturn("2019-10-29");
		when(request.getParameter("sellDate")).thenReturn("2026-10-29");
		when(request.getParameter("watchlist")).thenReturn("yes");
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Please enter the number of shares to buy."));
//		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("addwatch").delete();
		
		
	}
	
	
	
	@Test
	public void testAddtoWatchlistWrongCode() throws IOException {
	
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("sss");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("addwatch");
		when(request.getParameter("stockCode")).thenReturn("sss");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2019-10-29");
		when(request.getParameter("sellDate")).thenReturn("2020-10-29");
		when(request.getParameter("watchlist")).thenReturn("yes");
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Please enter the right stock code"));
//		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("addwatch").delete();
		
		
	}
	
	
	@Test
	public void testAddtoWatchlisToEarly() throws IOException {
		
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);

		
		when(request.getParameter("stockCode")).thenReturn("IBM");
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("addwatch");
		when(request.getParameter("stockCode")).thenReturn("MSFT");
		when(request.getParameter("currPrice")).thenReturn("100");
		when(request.getParameter("numShares")).thenReturn("10");
		when(request.getParameter("buyInDate")).thenReturn("2014-10-29");
//		when(request.getParameter("sellDate")).thenReturn("2020-10-29");
		when(request.getParameter("watchlist")).thenReturn("yes");
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PortfolioServlet ps = new PortfolioServlet();
		
		try {
			ps.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.print(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Buy in date must be within the last year!"));
//		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("addwatch").delete();
		
		
	}
}
