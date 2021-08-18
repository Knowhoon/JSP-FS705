package com.FS705.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FS705.dao.LogDAO;
import com.FS705.dao.SportsDAO;
import com.FS705.dto.CommentDTO;
import com.FS705.dto.LogDTO;
import com.FS705.util.Util;

@WebServlet("/commentInput")
public class CommentInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentInput() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String id = "";
		if(session.getAttribute(id) != null) {
			id = (String)session.getAttribute("id");
		}
		
		LogDTO logDto = new LogDTO();
				
		logDto.setLogIp(Util.getIP(request));
		logDto.setLogTarget("CommentInput");
		logDto.setLogdId((String)session.getAttribute(id));
		logDto.setLogEtc(request.getHeader("User-Agent"));
		logDto.setLogMethod("post");
		LogDAO.insertLog(logDto);
		
//		if(request.getParameter("ccontent") != null
//				&& request.getParameter("bno") != null
//				&& (Util.str2Int2(request.getParameter("bno")) != 0)
//				&& session.getAttribute("id") != null
//				&& session.getAttribute("name") != null){
		
		int test = 1;
		if(test == 1) {
			
			int result = 0;
			
			CommentDTO cmt = new CommentDTO();
			cmt.setBno(Util.str2Int(request.getParameter("bno")));
			cmt.setCcontent(request.getParameter("ccontent"));
			cmt.setId("kwon");
			cmt.setCip(Util.getIP(request));
			
			SportsDAO dao = SportsDAO.getInstance();
			result = dao.commentInput(cmt);

			if(result == 1) {
				response.sendRedirect("./sportsDetail?bno="+cmt.getBno());
			} else {
				response.sendRedirect("./error?code=commentInputError1");
			}
		} else {
			response.sendRedirect("./error?code=commentInputError2");
		}
		
	}

}
