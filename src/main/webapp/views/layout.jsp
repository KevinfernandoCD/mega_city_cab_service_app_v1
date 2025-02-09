<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="session.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${param.pageTitle}" /></title>
    <link rel="stylesheet" href="../styles/<c:out value="${param.styleSheet}" />">
    <link rel="stylesheet" href="../styles/index.css">
    <link rel="stylesheet" href="../styles/table-styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="container">
        <%@ include file="sidebar.jsp" %>
        <main class="main-content">
            <jsp:include page="${param.contentPage}" flush="true"/>
        </main>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
