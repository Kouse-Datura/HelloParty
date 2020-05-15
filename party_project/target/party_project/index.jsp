<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/4
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="upload/studentData" method="post" enctype="multipart/form-data">
        选择上传文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传" />
    </form><br>
    <hr>

    <h1>activist</h1>
    <form action="upload/activistData" method="post" enctype="multipart/form-data">
        选择上传文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传" />
    </form><br>
    <hr>

    <h1>applicant</h1>
    <form action="upload/applicantData" method="post" enctype="multipart/form-data">
        选择上传文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传" />
    </form><br>
    <hr>

    <h1>inspector</h1>
    <form action="upload/inspectorData" method="post" enctype="multipart/form-data">
        选择上传文件：<input type="file" name="upload"/><br/>
        <input type="submit" value="上传" />
    </form><br>
    <hr>

    <a href="publicity/third">generate</a>
</body>
</html>
