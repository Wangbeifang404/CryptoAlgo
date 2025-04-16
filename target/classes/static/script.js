const baseURL = "http://localhost:8080/api";

// 各服务对应支持的算法
const algorithms = {
  symmetric: ["aes", "sm4", "rc6"],
  hash: ["sha1", "sha256", "sha3", "ripemd160", "hmac-sha1", "hmac-sha256", "pbkdf2"],
  encoding: ["base64", "utf8"],
  asymmetric: ["rsa", "rsa-sha1", "ecdsa-sign", "ecdsa-verify", "ecc"]
};

let currentService = "symmetric";
let currentAlgorithm = "aes";

// 页面初始化
window.onload = function () {
  selectService(currentService);
};

function selectService(service) {
  currentService = service;
  const algoSelect = document.getElementById("algorithmSelect");

  // 高亮当前按钮
  document.querySelectorAll(".service-tabs button").forEach(btn => {
    btn.classList.remove("active");
    if (btn.textContent.includes(getServiceLabel(service))) {
      btn.classList.add("active");
    }
  });

  // 清空并加载对应算法选项
  algoSelect.innerHTML = "";
  algorithms[service].forEach(algo => {
    const option = document.createElement("option");
    option.value = algo;
    option.text = algo.toUpperCase();
    algoSelect.appendChild(option);
  });

  currentAlgorithm = algorithms[service][0];
  resetFields();
}

function getServiceLabel(service) {
  return {
    symmetric: "对称",
    hash: "哈希",
    encoding: "编码",
    asymmetric: "公钥"
  }[service];
}

function resetFields() {
  currentAlgorithm = document.getElementById("algorithmSelect").value;
  document.getElementById("inputText").value = "";
  document.getElementById("outputText").value = "";
  document.getElementById("keyInput").style.display =
    currentService === "symmetric" || algoNeedsKey(currentAlgorithm) ? "inline-block" : "none";
  // 显示/隐藏签名输入框（仅对于 ECDSA 验证需要）
  document.getElementById("signatureInput").style.display =
    currentAlgorithm === "ecdsa-verify" ? "inline-block" : "none";

  inputText.value = currentAlgorithm === "ecc" ? "ECC 仅用于密钥演示" : "";  // ECC 显示提示文本
  // 动态创建 ECC 按钮
  const buttons = document.querySelectorAll(".buttons button");
  if (currentAlgorithm === "ecc") {
    buttons[0].textContent = "获取公钥";  // 修改为获取公钥
    buttons[1].textContent = "获取私钥";  // 修改为获取私钥
  } else {
    buttons[0].textContent = "执行";
    buttons[1].textContent = "反向";
  }
}

function algoNeedsKey(algo) {
  return ["hmac-sha1", "hmac-sha256", "pbkdf2"].includes(algo);
}

function run(mode) {
  const input = document.getElementById("inputText").value;
  const key = document.getElementById("keyInput").value;
  const signature = document.getElementById("signatureInput").value;  // 获取签名
  const outputBox = document.getElementById("outputText");

  if (currentAlgorithm !== "ecc" && !input) {
    alert("请输入原文内容");
    return;
  }

  let url = "", body = {}, method = "POST";

  if (currentService === "symmetric") {
    url = `${baseURL}/symmetric/${currentAlgorithm}/${mode}`;
    body = { input, key };
  } else if (currentService === "hash") {
    url = `${baseURL}/hash/${currentAlgorithm}`;
    body = algoNeedsKey(currentAlgorithm) ? { input, key } : { input };
  } else if (currentService === "encoding") {
    url = `${baseURL}/encoding/${currentAlgorithm}/${mode}`;
    body = { input };
  } else if (currentService === "asymmetric") {
    if (currentAlgorithm === "ecdsa-sign") {
      url = `${baseURL}/asymmetric/ecdsa/sign`;
      body = { input };
    } else if (currentAlgorithm === "ecdsa-verify") {
      url = `${baseURL}/asymmetric/ecdsa/verify`;
      body = { input,signature };
    } else if (currentAlgorithm === "ecc") {
            // ECC 操作：获取公钥或私钥
            if (mode === 'encrypt') {
              url = `${baseURL}/asymmetric/ecc/public-key`;  // 获取公钥
              method = 'GET';
            } else if (mode === 'decrypt') {
              url = `${baseURL}/asymmetric/ecc/private-key`;  // 获取私钥
              method = 'GET';
            }
      body = undefined; // GET 请求不需要 body，确保为空
    } else {
      url = `${baseURL}/asymmetric/${currentAlgorithm}/encrypt`;
      body = { input };
    }
  }

  fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  })
    .then(res => res.text())
    .then(data => outputBox.value = data)
    .catch(err => alert("请求失败：" + err));
}
