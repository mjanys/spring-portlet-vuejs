<%@ page import="cz.janys.portlet.todo.TodoConstants" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="init.jspf" %>

<portlet:resourceURL var="todosUrl" id="<%=TodoConstants.TODOS_RESOURCE%>"/>


<div id="${ns}todo">
    <todo todos-url="${todosUrl}" inline-template>
        <%@include file="components/todo_template.html"%>
    </todo>
</div>

<script type="text/javascript">
    new Vue({
        el: '#${ns}todo',
        components: {
            todo: TodoVue
        }
    });
</script>
