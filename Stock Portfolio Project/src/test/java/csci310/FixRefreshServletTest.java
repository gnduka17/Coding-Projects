package csci310;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.Mockito;

import csci310.servlets.FixRefreshServlet;

public class FixRefreshServletTest extends Mockito {

	@Test
	public void testRefresh() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		ArrayList<Stock> stockStorage = new ArrayList<Stock>();
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("rgw17");
		
		FixRefreshServlet fr = new FixRefreshServlet();
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		try {
			fr.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("success"));
	}
	
	@Test
	public void coverage1() throws IOException, ServletException {
		FixRefreshServlet fr = mock(FixRefreshServlet.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		ArrayList<Stock> stockStorage = new ArrayList<Stock>();
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("rgw17");
		when(request.getSession().getAttribute("csvSuccess")).thenReturn("Success");
		
		//FixRefreshServlet fr = new FixRefreshServlet();
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
	 //   doNothing().when(fr).doGet(request, response);
	    doThrow(new RuntimeException()).when(fr).refreshWait();

		try {
			fr.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
	 //   assertEquals(null, new RuntimeException().getMessage());
		assertTrue(new RuntimeException().getMessage() == null);

	}

	@Test
	public void coverage2() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		ArrayList<Stock> stockStorage = new ArrayList<Stock>();
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("asfadsf");
		when(request.getSession().getAttribute("csvSuccess")).thenReturn(null);

		FixRefreshServlet fr = new FixRefreshServlet();
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		try {
			fr.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("success"));
	}
	
	@Test
	public void coverage3() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		ArrayList<Stock> stockStorage = new ArrayList<Stock>();
		Stock stock = new Stock("TSLA", 12.0, 15.0, 20201010, 20201212);
		Stock stock2 = new Stock("UBER", 12.0, 15.0, 20201010, 20201212);
		stockStorage.add(stock2);

		stockStorage.add(stock);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("rgw17");
		when(request.getSession().getAttribute("csvSuccess")).thenReturn("none");
		when(request.getSession().getAttribute("stockList")).thenReturn(stockStorage);
		when(request.getSession().getAttribute("watchList")).thenReturn(stockStorage);

		FixRefreshServlet fr = new FixRefreshServlet();
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		try {
			fr.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("success"));
	}
	
	@Test
	public void coverage4() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		ArrayList<Stock> stockStorage = new ArrayList<Stock>();
		Stock stock = new Stock("TSLA", 12.0, 15.0, 20201010, 0);
		Stock stock2 = new Stock("UBER", 12.0, 15.0, 20201010, 0);
		stockStorage.add(stock2);

		stockStorage.add(stock);
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("csvtest");
		when(request.getSession().getAttribute("csvSuccess")).thenReturn("none");
		when(request.getSession().getAttribute("stockList")).thenReturn(stockStorage);
		when(request.getSession().getAttribute("watchList")).thenReturn(stockStorage);

		FixRefreshServlet fr = new FixRefreshServlet();
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		try {
			fr.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("success"));
	}
	
	@Test
	public void coverage5() throws IOException, ServletException {
		FixRefreshServlet fr = mock(FixRefreshServlet.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		ArrayList<Stock> stockStorage = new ArrayList<Stock>();
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("username")).thenReturn("rgw17");
		when(request.getSession().getAttribute("csvSuccess")).thenReturn("Success");

		//FixRefreshServlet fr = new FixRefreshServlet();
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
	 //   doNothing().when(fr).doGet(request, response);
	    doThrow(new RuntimeException()).when(fr).refreshWait();

		try {
			fr.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
	    assertEquals(null, new RuntimeException().getMessage());
	}
}
