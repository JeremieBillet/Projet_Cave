package com.intiformation.projet.caveavin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intiformation.projet.caveavin.dao.AdminDAOImpl;
import com.intiformation.projet.caveavin.dao.ClientDAOImpl;
import com.intiformation.projet.caveavin.dao.IAdminDAO;
import com.intiformation.projet.caveavin.dao.IClientDAO;

public class AuthentificationBean extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		String destroySession = request.getParameter("destroy");
		
		if ("true".equals(destroySession)) {
			
			session.invalidate();
			
		}//end if
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}//end doGet()

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomUser = request.getParameter("Username");
		String motDePasse = request.getParameter("Password");
		
		Object choixConnection = request.getParameter("btn btn-primary");
		
		List<String> listeMessagesErreurs = new ArrayList<>();
		
		if (choixConnection.equals("Se Connecter")) {
			
			if ("".equals(nomUser)) {
				listeMessagesErreurs.add("Vous devez renseigner votre adresse mail");
			}//end if
			
			if ("".equals(motDePasse)) {
				listeMessagesErreurs.add("Vous devez renseigner votre mot de passe");
			}//end if
			
			if (listeMessagesErreurs.size() > 0) {
				request.setAttribute("message_erreurs", listeMessagesErreurs);
				
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}//end if
			
			if (listeMessagesErreurs.isEmpty()) {
				
				IClientDAO clientDAO = new ClientDAOImpl();
				IAdminDAO adminDAO = new AdminDAOImpl();
				
				if (clientDAO.isClientExist(nomUser, motDePasse)) {

					HttpSession session = request.getSession(true);
					session.setAttribute("isLogged", true);
					session.setAttribute("nom_client", nomUser);
					
					request.getRequestDispatcher("/index.jsp").forward(request, response);

				} else if (adminDAO.isAdminExist(nomUser, motDePasse)) {
					
					HttpSession session = request.getSession(true);
					session.setAttribute("isLogged", true);
					session.setAttribute("nom_admin", nomUser);
					
					request.getRequestDispatcher("/index.jsp").forward(request, response);

				} else {
					listeMessagesErreurs.add("Le nom ou le mot de passe est incorrect");
					request.setAttribute("messages_erreurs", listeMessagesErreurs);
				
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}//end if-else-if - vérif
				
			}//end if - connection
			
		}//end if-else - choix connection
		
	}//end doPost()
	
	

}//end servlet
