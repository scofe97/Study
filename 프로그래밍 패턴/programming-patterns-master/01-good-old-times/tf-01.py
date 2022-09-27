#!/usr/bin/env python
import sys, os, string

# 보조 기억 장치를 연다
def touchopen(filename, *args, **kwargs):
    try:
        os.remove(filename)
    except OSError:
        pass
    open(filename, "a").close() # "touch" file
    return open(filename, *args, **kwargs)

data = []

print(sys.argv)

f = open('../stop_words.txt')
data = [f.read(1024).split(',')] # data[0] holds the stop words
print(data)
f.close()

data.append([]) # data[1]은 (최대 80자인) 줄
data.append(None) # data[2]는 단어의 시작 문자 색인
data.append(0) # data[3]은 문자에 대한 색인이며 i=0
data.append(False) # data[4]는 단어를 찾았는지 여부를 나타내는 플래그
data.append('') # data[5]는 해당 단어
data.append('') # data[6]은 단어, NNNN
data.append(0) #data[7]은 빈도
print(data)


# Open the secondary memory
word_freqs = touchopen('word_freqs', 'rb+')
# 입력 파일을 연다
f = open(sys.argv[1])
# Loop over input file's lines
while True:
    data[1] = [f.readline()] 
    if data[1] == ['']: # 입력 파일 끝
        break
    if data[1][0][len(data[1][0])-1] != '\\n': #\\n으로 끝나지 않으면
        data[1][0] = data[1][0] + '\\n' # \\n을 추가한다
    data[2] = None
    data[3] = 0 
    # Loop over characters in the line
    for c in data[1][0]: # elimination of symbol c is exercise
        if data[2] == None:
            if c.isalnum():
                # We found the start of a word
                data[2] = data[3]
        else:
            if not c.isalnum():
                # We found the end of a word. Process it
                data[4] = False 
                data[5] = data[1][0][data[2]:data[3]].lower()
                # Ignore words with len < 2, and stop words
                if len(data[5]) >= 2 and data[5] not in data[0]:
                    # Let's see if it already exists
                    while True:
                        data[6] = str(word_freqs.readline().strip(), 'utf-8')
                        if data[6] == '':
                            break;
                        data[7] = int(data[6].split(',')[1])
                        # word, no white space
                        data[6] = data[6].split(',')[0].strip() 
                        if data[5] == data[6]:
                            data[7] += 1
                            data[4] = True
                            break
                    if not data[4]:
                        word_freqs.seek(0, 1) # Needed in Windows
                        word_freqs.write(bytes("%20s,%04d\n" % (data[5], 1), 'utf-8'))
                    else:
                        word_freqs.seek(-26, 1)
                        word_freqs.write(bytes("%20s,%04d\n" % (data[5], data[7]), 'utf-8'))
                    word_freqs.seek(0,0)
                # Let's reset
                data[2] = None
        data[3] += 1
# We're done with the input file
f.close()
word_freqs.flush()

# PART 2
# Now we need to find the 25 most frequently occuring words.
# We don't need anything from the previous values in memory
del data[:]

# Let's use the first 25 entries for the top 25 words
data = data + [[]]*(25 - len(data))
data.append('') # data[25] is word,freq from file
data.append(0)  # data[26] is freq

# Loop over secondary memory file
while True:
    data[25] = str(word_freqs.readline().strip(), 'utf-8')
    if data[25] == '': # EOF
        break
    data[26] = int(data[25].split(',')[1]) # Read it as integer
    data[25] = data[25].split(',')[0].strip() # word
    # Check if this word has more counts than the ones in memory
    for i in range(25): # elimination of symbol i is exercise
        if data[i] == [] or data[i][1] < data[26]:
            data.insert(i, [data[25], data[26]]) 
            del data[26] #  delete the last element
            break
            
for tf in data[0:25]: # elimination of symbol tf is exercise
    if len(tf) == 2:
        print(tf[0], ' - ', tf[1])
# We're done
word_freqs.close()
