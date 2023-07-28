/**
 * 
 * @param {string} key 
 * @returns document element
 */
const query = (key) => {
  return document.querySelector(key)
}
/**
 * 
 * @param {string} key 
 * @returns node list
 */
const queryAll = (key) => {
  return document.querySelectorAll(key)
}
/**
 * 
 * @param {string} userName 
 * @returns true or false
 */
const checkUserName = (userName) => {
  return /^20(19|[2-9][0-9])\d{8}$/.test(userName)
}
/**
 * 
 * @param {string} password 
 * @returns true or false
 */
const checkPassword = (password) => {
  return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])|(?=.*\d)(?=.*[A-Z])|(?=.*\d)(?=.*[a-z])|(?=.*[a-z])(?=.*[!@#$%^&*])|(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]+$/.test(
    password
  )
}
/**
 * 
 * @param {HTMLInputElement} element 
 */
const setErrorState = (element) => {
  element.classList.add('border-error');
}
/**
 * 
 * @param {HTMLInputElement} element 
 */
const clearErrorState = (element) => {
  element.classList.remove('border-error')
}
/**
 * 
 * @param {HTMLElement} element 
 * @param {string} message 
 */
const setHelpMessage = (element, message) => {
  element.innerHTML = message
}
/**
 * @description 学号校验
 * @returns true or false
 */
const varifyUserName = () => {
  if (!username.value) {
    setErrorState(username)
    setHelpMessage(usernameHelp, '请输入学号')
    return false
  }
  if (!checkUserName(username.value)) {
    setErrorState(username)
    setHelpMessage(usernameHelp, '学号格式不正确')
    return false
  }
  setHelpMessage(usernameHelp, '')
  clearErrorState(username)
  return true
}
/**
 * @description 密码校验
 * @returns true or false
 */
const varifyPassword = () => {
  if (!password.value) {
    setErrorState(password)
    setHelpMessage(passwordHelp, '请输入密码')
    return false
  }
  if (!checkPassword(password.value)) {
    setErrorState(password)
    setHelpMessage(passwordHelp, '密码格式不正确')
    return false
  }
  setHelpMessage(passwordHelp, '')
  clearErrorState(password)
  return true
}
/**
 * @description 关闭 messagebox
 */
function close() {
  this.style.display = 'none'
}

const username = query('#username')
const password = query('#password')
const usernameHelp = query('#username-help')
const passwordHelp = query('#password-help')
username.addEventListener('blur', varifyUserName)
password.addEventListener('blur', varifyPassword)

const form = query("#form")
const login = query('#login')
login.addEventListener('click', () => {
  if (varifyPassword() && varifyUserName()) {
    form.submit()
  }
})

const messageBox = queryAll('.message-box')
messageBox.forEach((item) => {
  item.addEventListener('click', close)
})

const rejectButton = query('.reject')
rejectButton.addEventListener('click', () => {
  const link = document.createElement('a');
  // 导航页网址
  link.href = ''
  link.click()
})