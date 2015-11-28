<%@tag description="Input Row" pageEncoding="UTF-8" %>
<%@tag dynamic-attributes="attributes" %>

<%@include file="../init.jspf" %>

<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="path" type="java.lang.String" required="true" %>
<%@attribute name="labelCode" type="java.lang.String" required="true" %>
<%@attribute name="model" type="java.lang.String" required="false" %>

<jsp:doBody var="body"/>

<f:row path="${path}" inputId="${id}">
    <f:label labelCode="${labelCode}"/>
    <c:choose>
        <c:when test="${not empty body}">
            <jsp:doBody/>
        </c:when>
        <c:otherwise>
            <f:input model="${model}"/>
        </c:otherwise>
    </c:choose>
    <f:errors/>
</f:row>
