<%@tag description="Input Row" pageEncoding="UTF-8" %>
<%@tag body-content="empty" dynamic-attributes="attributes" %>

<%@include file="../init.jspf" %>

<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="path" type="java.lang.String" required="true" %>
<%@attribute name="labelCode" type="java.lang.String" required="true" %>
<%@attribute name="model" type="java.lang.String" required="false" %>

<f:row path="${path}" inputId="${id}">
    <f:label labelCode="${labelCode}"/>
    <f:input model="${model}"/>
    <f:errors/>
</f:row>
