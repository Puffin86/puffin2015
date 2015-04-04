<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%
	session.invalidate();
	response.sendRedirect("login.jsp");
%>