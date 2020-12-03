
package csci310;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.opencsv.CSVReader;

import csci310.servlets.CsvServlet;

/**
 * @author rw17a
 *
 */
public class CsvServletTest extends Mockito {

	@Mock
	HttpSession session;
	
	private String testCSVFile = "ERIC,12,10/27/2020,10/31/2020 \n TSLA,24,10/25/2020,10/30/2020";
	private String file = "CRM,12,10/25/2020,10/31/2020";
	private String DNEStock = "JOSH,12,10/25/2020,10/31/2020";
	private String badFormat = "12,10/25/2020,10/31/2020";
	private String badFormat2 = ",";
	private String badFormat3 = "MA,12,10/25/1900,10/31/1850";
	
	@Test
	public void repetitiveAdd() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		 
		try {
			
			when(request.getSession().getAttribute("testCase")).thenReturn("true");
			when(request.getSession().getAttribute("csvFile")).thenReturn(testCSVFile);
			when(request.getSession().getAttribute("username")).thenReturn("Barrett");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
        
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test 1");
		writer.flush();
		System.out.println("real output is " +stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Duplicate"));
	}
	
	
	@Test
	public void successfulAdd() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		
		when(request.getSession().getAttribute("testCase")).thenReturn("true");
		when(request.getSession().getAttribute("csvFile")).thenReturn(file);
		when(request.getSession().getAttribute("username")).thenReturn("Barrett");
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test good_add");
		writer.flush();
		System.out.println("real output" + stringWriter.toString());
		assertTrue(stringWriter.toString().contains("succesfully"));
	}
	
	@Test
	public void databaseDNE() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		try {
			
			when(request.getSession().getAttribute("testCase")).thenReturn("true");
			when(request.getSession().getAttribute("csvFile")).thenReturn(file);
			when(request.getSession().getAttribute("username")).thenReturn("Frank");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		    
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test 2");
		writer.flush();
		System.out.println("real output" + stringWriter.toString());
		assertTrue(stringWriter.toString().contains("DNE"));
	}
	
	@Test
	public void catchBlock1() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		try {
			when(request.getSession().getAttribute("testCase")).thenReturn("true");
			when(request.getSession().getAttribute("csvFile")).thenReturn(file);
			when(request.getSession().getAttribute("username")).thenReturn("Jake");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		    
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test 2");
		writer.flush();
		System.out.println("real output" + stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Catch block 1"));
	}
	@Test
	public void catchBlock2() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		try {
		
			when(request.getSession().getAttribute("testCase")).thenReturn("true");
			when(request.getSession().getAttribute("csvFile")).thenReturn(DNEStock);
			when(request.getSession().getAttribute("username")).thenReturn("Barrett");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		    
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test 2");
		writer.flush();
		System.out.println("real output" + stringWriter.toString());
		assertTrue(stringWriter.toString().contains("does not exist"));
	}
	
	@Test
	public void badCSVFormat() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		try {
			
			when(request.getSession().getAttribute("testCase")).thenReturn("true");
			when(request.getSession().getAttribute("csvFile")).thenReturn(badFormat);
			when(request.getSession().getAttribute("username")).thenReturn("Barrett");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		    
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test 6");
		writer.flush();
		System.out.println("real output" + stringWriter.toString());
		assertTrue(stringWriter.toString().contains("format"));
	}
	
	@Test
	public void badCSVFormat2() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		try {

			when(request.getSession().getAttribute("testCase")).thenReturn("true");
			when(request.getSession().getAttribute("csvFile")).thenReturn(badFormat2);
			when(request.getSession().getAttribute("username")).thenReturn("Barrett");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		   
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test 6");
		writer.flush();
		System.out.println("real output" + stringWriter.toString());
		assertTrue(stringWriter.toString().contains("format"));
	}
	
	@Test
	public void badCredentials() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);		
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		try {
			when(request.getSession().getAttribute("testCase")).thenReturn("true");
			when(request.getSession().getAttribute("csvFile")).thenReturn(file);
			when(request.getSession().getAttribute("username")).thenReturn("Dan");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test credentials");
		writer.flush();
		System.out.println("real output" + stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Credentials"));
	} 
	
	@Test
	public void repetitive() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		 
		try {
			
			when(request.getSession().getAttribute("testCase")).thenReturn("asdfasdf");
			when(request.getSession().getAttribute("csvFile")).thenReturn(badFormat3);
			when(request.getSession().getAttribute("username")).thenReturn("Barrett");
			RequestDispatcher rd = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        CsvServlet ps = new CsvServlet();
        
		try {
			ps.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		System.out.println("test 8");
		writer.flush();
		System.out.println("real output is " +stringWriter.toString());
		assertTrue(stringWriter.toString().contains("invalid"));
	}
	
}
