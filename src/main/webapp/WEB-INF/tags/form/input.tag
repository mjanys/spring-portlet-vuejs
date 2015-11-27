<%@tag description="Input" pageEncoding="UTF-8" %>

<%@include file="../init.jspf" %>

<%@attribute name="model" type="java.lang.String" required="false" %>

<form:input path="${path}" id="${id}" cssClass="form-control" v-model="${model}"/>
<c:if test="${hasErrors}">
    <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
    <span id="inputError2Status" class="sr-only">(error)</span>
</c:if>