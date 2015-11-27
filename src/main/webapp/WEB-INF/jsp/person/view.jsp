<%--@elvariable id="items" type="java.util.List<cz.janys.iface.dto.PersonDto>"--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="init.jspf" %>

<div class="personView">
    <div class="panel panel-default">
        <!-- Default panel contents -->

        <!-- Table -->
        <%@include file="components/table.jspf"%>

        <div class="panel-footer">
            <portlet:renderURL var="createFormUrl">
                <portlet:param name="<%=PARAM_PAGE%>" value="<%=PERSON_FORM_CREATE_PAGE%>"/>
            </portlet:renderURL>
            <h:link href="${createFormUrl}">
                <spring:message code="label-create-person" var="createLabel"/>
                <bs:button text="${createLabel}" cssClass="btn-default"/>
            </h:link>
        </div>
    </div>
</div>
