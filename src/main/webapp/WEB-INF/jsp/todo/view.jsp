<%@ page import="cz.janys.portlet.todo.TodoConstants" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="init.jspf" %>

<portlet:resourceURL var="getUrl" id="<%=TodoConstants.GET_RESOURCE%>"/>

<div id="${ns}todoapp">
    <section class="todoapp">
        <header class="header">
            <h1>todos</h1>
            <input class="new-todo"
                   autofocus autocomplete="off"
                   placeholder="What needs to be done?"
                   v-model="newTodo"
                   @keyup.enter="addTodo">
        </header>
        <section class="main"
                 v-show="todos.length"
                 v-cloak>
            <input class="toggle-all" type="checkbox"
                   v-model="allDone"/>
            <ul class="todo-list">
                <li class="todo"
                    v-for="todo in filteredTodos"
                    :class="{completed: todo.completed, editing: todo == editedTodo}">
                    <div class="view">
                        <input class="toggle" type="checkbox" v-model="todo.completed">
                        <label @dblclick="editTodo(todo)">{{todo.title}}</label>
                        <button class="destroy" @click="removeTodo(todo)"></button>
                    </div>
                    <input class="edit" type="text"
                           v-model="todo.title"
                           v-todo-focus="todo == editedTodo"
                           @blur="doneEdit(todo)"
                           @keyup.enter="doneEdit(todo)"
                           @keyup.esc="cancelEdit(todo)">
                </li>
            </ul>
        </section>
        <footer class="footer" v-show="todos.length" v-cloak>
				<span class="todo-count">
					<strong v-text="remaining"></strong> {{remaining | pluralize 'item'}} left
				</span>
            <ul class="filters">
                <li><a href="#/all" :class="{selected: visibility == 'all'}" @click="showAll">All</a></li>
                <li><a href="#/active" :class="{selected: visibility == 'active'}" @click="showActive">Active</a></li>
                <li><a href="#/completed" :class="{selected: visibility == 'completed'}" @click="showCompleted">Completed</a>
                </li>
            </ul>
            <button class="clear-completed" @click="removeCompleted" v-show="todos.length > remaining">
                Clear completed
            </button>
        </footer>
    </section>

</div>

<script type="text/javascript">
    new TodoVue({
        el: '#${ns}todoapp',
        data: {
            todos: [],
            newTodo: '',
            editedTodo: null,
            visibility: 'all'
        },
        getUrl: '${getUrl}'
    });
</script>
