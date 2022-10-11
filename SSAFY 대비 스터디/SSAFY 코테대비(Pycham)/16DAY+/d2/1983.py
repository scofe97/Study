for tnt in range(1, int(input())+1):
    grade = {0: 'A+',1: 'A0',2: 'A-',3: 'B+',4: 'B0',5: 'B-',6: 'C+',7: 'C0',8: 'C-',9: 'D0',}

    snt, num = map(int, input().split())

    student = [list(map(int ,input().split())) for _ in range(snt)]

    student_grade = [0] * snt

    for i in range(len(student)):
        student_grade[i] = student[i][0] * 0.35 +  student[i][1] * 0.45 + student[i][2] * 0.20

    find_student = student_grade[num-1]

    student_grade.sort(reverse=True)
    rank = student_grade.index(find_student) // (snt//10)

    print(f'#{tnt} {grade[rank]}')


# 12:23