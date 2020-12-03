package csci310;

import static org.junit.Assert.assertTrue;



import csci310.servlets.LoginServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class LoginServletTest extends Mockito{
	@Mock
	HttpSession session;
	
	@Test
	public void testDoGet() throws IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);

		when(request.getParameter("username")).thenReturn("ericmanning8");
		when(request.getParameter("userpass")).thenReturn("test");
		//when(request.getParameter("logOut")).thenReturn("false");

		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/MainPage.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Login successful"));
	}
	
	@Test
	public void testNullInput() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("userpass")).thenReturn("");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Login error"));
	}
	
	@Test
	public void testPasswordEmpty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("username")).thenReturn("Barrett");
		when(request.getParameter("userpass")).thenReturn("");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.println("barrett debug "+ stringWriter.toString());
		assertTrue(stringWriter.toString().contains("wrong password"));
	}
	
	@Test
	public void testUsernameEmpty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("userpass")).thenReturn("idk");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Username empty"));
	}
	
	@Test
	public void wrongPassword() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("username")).thenReturn("ericmanning8");
		when(request.getParameter("userpass")).thenReturn("test2");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("wrong password"));
	}
	
	@Test
	public void incorrectDatabase() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("username")).thenReturn("databasetesting");
		when(request.getParameter("userpass")).thenReturn("asdfafds");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Database not found"));
	}
	
	@Test
	public void dispatcherError() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("username")).thenReturn("case2");
		when(request.getParameter("userpass")).thenReturn("asdfafds");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Dispatcher not found"));
	}
	
	@Test
	public void testLogout() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("ericmanning8");
		when(request.getParameter("userpass")).thenReturn("test");
		when(request.getParameter("logOut")).thenReturn("true");
		
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Login successful"));
	}
	
	@Test
	public void testStockList() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("Barrett");
		when(request.getParameter("userpass")).thenReturn("yellow");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Login successful"));
	}
	@Test
	public void testNotExist() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("ahsjdlandfl");
		when(request.getParameter("userpass")).thenReturn("yellow");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("wrong password"));
	}
	
	@Test
	public void testNotExist1() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("ahsjdlandfl");
		when(request.getParameter("userpass")).thenReturn("");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Password empty"));
	}
	
	@Test
	public void testWatchList() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("watcher");
		when(request.getParameter("userpass")).thenReturn("123");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Login successful"));
	}
	@Test
	public void testLockLogin1() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("attempt2");
		when(request.getParameter("userpass")).thenReturn("234");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		
		Firestore db2 = FirestoreClient.getFirestore();
		assertTrue(stringWriter.toString().contains("wrong password"));
		int attempt = 2;
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("attemp2").update("attempts", attempt);
	}
	
	@Test
	public void testLockLogin2() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("attemp1");
		when(request.getParameter("userpass")).thenReturn("234");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		
		Firestore db2 = FirestoreClient.getFirestore();
		assertTrue(stringWriter.toString().contains("wrong password"));
		int attempt = 1;
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("attemp1").update("attempts", attempt);
	}
	
	@Test
	public void testLockLogin3() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("attemp0");
		when(request.getParameter("userpass")).thenReturn("234");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		
		Firestore db2 = FirestoreClient.getFirestore();
		assertTrue(stringWriter.toString().contains("wrong password"));
		int attempt = 0;
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("attemp0").update("attempts", attempt);
	}
	
	@Test
	public void testLockLogin4() throws IOException {
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		
		Firestore db2 = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("attemp0").update("validLoginTime", Timestamp.now().getSeconds());

		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("attemp0");
		when(request.getParameter("userpass")).thenReturn("234");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		
		assertTrue(stringWriter.toString().contains("wrong password"));
		int attempt = 0;
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult2 = db2.collection("users").document("attemp0").update("attempts", attempt);
	}
	
	@Test
	public void testLockLogin5() throws IOException {
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		
		Firestore db2 = FirestoreClient.getFirestore();
		
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("attemp0").update("validLoginTime", Timestamp.now().getSeconds());

		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("attemp1");
		when(request.getParameter("userpass")).thenReturn("234");
		when(request.getParameter("logOut")).thenReturn("true");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/index.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

		LoginServlet ls = new LoginServlet();
		
		try {
			ls.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		
		assertTrue(stringWriter.toString().contains("wrong password"));
		int attempt = 1;
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult2 = db2.collection("users").document("attemp1").update("attempts", attempt);
	}
	


}