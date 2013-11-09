package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patterns.GameIdentityMap;
import patterns.UOW;
import models.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		UOW.newCurrent();
		UOW unit = UOW.getCurrent();
		Game gm = GameIdentityMap.getInstance().get(1);
		unit.registerClean(gm);
		
		ChangeName(gm, "Hello world!!");
		Game newGame = new Game(0, "Title", "Desc" , 15.4 , 24);
		AddGame(newGame);
		Commit();
		int  s = 5;
	}

	private void ChangeName(Game gm, String name) {
		
		gm.Name = name;
		UOW.getCurrent().registerDirty(gm);
	}
	
	private void AddGame(Game gm) {
	
	UOW.getCurrent().registerNew(gm);
	}
	
	private void Commit() {
		UOW.getCurrent().commit();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
