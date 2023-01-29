// 기본타입
let age: number = 24;
age = 12;

let userName: string;
userName = 'Max';

let isInstructor: boolean;
isInstructor = true;


// 배열, 객체타입
let hobbies: string[];
hobbies = ["Sports", 'Cooking'];

let person: {
    name: string;
    age: number;
};
person ={
    name: 'Max',
    age: 32
}

// person = {
//     isEmployee: true
// }


// Type inference
let course = "React - The Complete Guide"
// course = 12341; // -> 문자로 자동인식해서 숫자넣으니 에러가남 (타입추론)


// Union 타입
let course2: string | number = "React - The Complete Guide"
course2 = 12341; // 유니온 타입으로 지정되서 숫자도 들어감


// type 키워드 사용
type Person = {
    name: string;
    age: number;
};
let person2: Person = {
    name: 'Max',
    age: 32
}


// Functions & types
function add(a: number,b: number) {
    return a + b;
}

function print(value: any): void{
    console.log(value);
}


// Generics
function insertAtBeginning<T>(array: T[], value: T){
    const newArray = [value, ...array];
    return newArray;
}

const demoArray = [1, 2, 3];
const updatedArray = insertAtBeginning(demoArray, -1); // [-1,1,2,3]
const stringArray = insertAtBeginning(['a','b','c'], 'd');
stringArray[0].split('');