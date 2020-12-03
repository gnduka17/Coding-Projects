package csci310;

import static org.junit.Assert.assertTrue;




import csci310.servlets.LoginServlet;
import csci310.servlets.RegisterServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class RegisterServletTest extends Mockito{

	@Test
	public void bothParamEmpty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("");
		when(request.getParameter("new_userpass1")).thenReturn("");
		when(request.getParameter("new_userpass2")).thenReturn("");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Both are null"));
	}
	@Test
	public void usernameEmpty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("");
		when(request.getParameter("new_userpass1")).thenReturn("asdfasdf");
		when(request.getParameter("new_userpass2")).thenReturn("asdfasdf");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Username is null"));
	}
	@Test
	public void passOneEmpty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("dank");
		when(request.getParameter("new_userpass1")).thenReturn("");
		when(request.getParameter("new_userpass2")).thenReturn("asdfasdf");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Password is empty"));
	}
	@Test
	public void passTwoEmpty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("dank");
		when(request.getParameter("new_userpass1")).thenReturn("asdfasdf");
		when(request.getParameter("new_userpass2")).thenReturn("");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Password is empty"));
	}
	@Test
	public void passwordMismatch() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("dank");
		when(request.getParameter("new_userpass1")).thenReturn("asdfasdf");
		when(request.getParameter("new_userpass2")).thenReturn("hey");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Password mismatch"));
	}
	@Test
	public void secondPasswordEmpty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("");
		when(request.getParameter("new_userpass1")).thenReturn("");
		when(request.getParameter("new_userpass2")).thenReturn("fruit");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Username is null"));
	}
	@Test
	public void successfulRegister() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("Fauci");
		when(request.getParameter("new_userpass1")).thenReturn("doctor");
		when(request.getParameter("new_userpass2")).thenReturn("doctor");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		System.out.println("// " + stringWriter.toString());
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials2.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		if(FirebaseApp.getApps().isEmpty()) { 
            FirebaseApp.initializeApp(options);
        }
		
		Firestore db2 = FirestoreClient.getFirestore();
		System.out.println(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Account creation successful"));
		ApiFuture<com.google.cloud.firestore.WriteResult> writeResult = db2.collection("users").document("Fauci").delete();
		
	}
	@Test
	public void registerExistingAccount() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("Barrett");
		when(request.getParameter("new_userpass1")).thenReturn("doctor");
		when(request.getParameter("new_userpass2")).thenReturn("doctor");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Account already exists"));
	}
	@Test
	public void nullCollection() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("nullCollection");
		when(request.getParameter("new_userpass1")).thenReturn("doctor");
		when(request.getParameter("new_userpass2")).thenReturn("doctor");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Database does not exist"));
	}
	@Test
	public void firebaseEdgeCase() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		when(request.getParameter("new_username")).thenReturn("edgeCase");
		when(request.getParameter("new_userpass1")).thenReturn("doctor");
		when(request.getParameter("new_userpass2")).thenReturn("doctor");
		
		RequestDispatcher rd = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher(eq("/Register.jsp"))).thenReturn(rd);
		
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RegisterServlet rs = new RegisterServlet();
		
		try {
			rs.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("fail");
		}
		writer.flush();
		assertTrue(stringWriter.toString().contains("Firebase already exists"));
	}
}