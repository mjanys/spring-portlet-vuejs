<%@ page import="cz.janys.portlet.todo.TodoConstants" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="init.jspf" %>
<%@include file="components/todo.jspf"%>

<div id="${ns}todo">
    <todo></todo>
</div>

<script type="text/javascript">
    new Vue({
        el: '#${ns}todo'
    });
</script>
