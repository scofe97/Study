// Spread
const numbers = [1, 2, 3];
const newNumbers = [...numbers, 4];

console.log(newNumbers); // [1, 2, 3, 4]

const person = {
  name: "Max",
};

const newPerson = {
  ...person,
  age: 28,
};

console.log(newPerson); // {name: 'Max', age: 28}

// rest 연산자
const filter = (...args) => {
  return args.filter((el) => el > 2);
};

console.log(filter(1, 2, 3, 4)); // [3, 4]
