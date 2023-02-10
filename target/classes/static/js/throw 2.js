function checkDuplicates() {
    var url = document.getElementById('url').value;
    var message = document.getElementById('urlP');

    var flag = true;;
    if (url.includes("https://www.youtube.com/@")) {
        alert('a')
      message.innerHTML = 'このURLは添付できません';
      flag = false;
    }
    
    if (flag) {
      return true;
    } else {
      return false;
    }
  
    
}