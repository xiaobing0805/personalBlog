<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<th data-field="creator" data-title="<spring:message code="content.creator" />" data-formatter="$.formatter.forMatter"></th>
<th data-field="operator" data-title="<spring:message code="content.operator" />" data-formatter="$.formatter.forMatter"></th>
<th data-field="createdTime" data-title="<spring:message code="content.created.time" />" data-formatter="$.formatter.timeForMatter"></th>
<th data-field="modifedTime" data-title="<spring:message code="content.modifed.time" />" data-formatter="$.formatter.timeForMatter"></th>