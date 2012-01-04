<%@ page contentType="text/html; charset=ISO-8859-1" %>
<!-- <link href="../assets/css/960gs.css" rel="stylesheet" type="text/css" /> -->

<div id="appboxeswrapper">
  <div class="grid_5">
    <div class="appbox">
        <p class="info">Sign into IMLS Platform</p>
        <div>
        <form name="frmAuth" id="frmAuth">
        	<div class="grid_2 alpha"><span style="float: right; vertical-align: baseline;">Username</span></div>
            <div class="grid_3 omega"><input name="username" type="text" /></div>
            <div class="clear">&nbsp;</div>
            
            <div class="grid_2 alpha">Password</div>
            <div class="grid_3 omega"><input name="password" type="password" /></div>
            <div class="clear">&nbsp;</div>
            
            <div>
            	<input name="btnSignIn" type="submit" id="btnSignIn" value="Sign In" />
            </div>
            <p>&nbsp;</p>
        </form>
        </div>
      <div class="appclickbox">
            <a href="#" title="IMLS Authentication">&raquo; IMLS Authentication</a>
        </div>
    </div>
  </div>
  <div class="clear">&nbsp;</div>
</div>