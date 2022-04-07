function hello() {
	return "hello";
}

async function helloAsync() {
	return "hello Async";
} 

console.log(hello());
console.log(helloAsync());
//hello
//Promise {<pending>}
//함수에 async를 붙이면 Promise를 반환하는 비동기 처리를 함

helloAsync().then(res) = {
	console.log(res);
});
//hello Async
//then을 사용하여 값을 가져올 수 있음