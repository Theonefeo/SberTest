var form = document.getElementById('myForm');
form.onsubmit = function(event) {
  var xhr = new XMLHttpRequest();
  var formData = new FormData(form);
  //open the request
  xhr.open('POST', '/core-api/logs')
  xhr.setRequestHeader("Content-Type", "application/json");

  //send the form data
  xhr.send(JSON.stringify(Object.fromEntries(formData)));

  xhr.onreadystatechange = function() {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      form.reset(); //reset form after AJAX success or do something else
    }
  }
  
}