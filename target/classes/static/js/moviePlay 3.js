function removeFeatureShare(input) {
    var inputValue = input.value;
    if (inputValue.includes("?feature=share")) {
      input.value = inputValue.replace("?feature=share", "");
    }
  }