Немного о работе данного кода.

Задание: написать программу на Java распаковывания строки. На вход поступает строка вида число[строка], на выход - строка, содержащая повторяющиеся подстроки.

Пример:
Вход: 3[xyz]2[xy]w
Выход: xyzxyzxyzxyxyw

Ограничения:
1) одно повторение может содержать другое.
2) допустимые символы на вход: латинские символы, числа, скобки
3) числа обозначают только число повторений
4) скобки используются только для обозначения повторяющихся строк

Возможные логи:
warning:
1) пустой файл
2) строка вида число[пусто] будет просто пропускаться, но в консоли выведется warning.
3) 
error:
1) данный файл не существует
2) перед открывающейся скобкой не число
3) перед буквой стоит число
4) перед закрывающейся скобкой нет строки символов
5) введены недопустимые символы
6) нет закрывающейся скобки
7) введено число 0 или число начинающееся на 0

При нахождении error будет выведен соответствующий лог и работа программы прекратится.
