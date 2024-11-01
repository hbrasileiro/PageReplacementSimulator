# PageReplacementSimulator

Este projeto implementa um simulador de substituição de página com quatro algoritmos diferentes: FIFO, LRU, Ótimo e Relógio. Esses algoritmos são usados em sistemas operacionais para gerenciar a memória e minimizar faltas de página, que ocorrem quando uma página necessária não está na memória principal e precisa ser carregada.

# Funcionalidades

O programa permite que o usuário insira uma sequência de páginas e o número de quadros de memória disponíveis. Ele calcula e exibe o número de faltas de página para cada um dos seguintes métodos de substituição de página:

FIFO (First-In, First-Out): O primeiro elemento que entra na memória é o primeiro a sair.
LRU (Least Recently Used): A página menos recentemente usada é substituída.
Ótimo (Optimal): Substitui a página que não será usada por mais tempo no futuro.
Relógio (Clock): Usa um ponteiro circular e bits de uso para decidir qual página substituir.
Estrutura do Código

O código é dividido em métodos que implementam cada algoritmo de substituição de página. Abaixo está um resumo de cada um:

FIFO: Utiliza uma fila (Queue) para armazenar as páginas na ordem em que foram carregadas. Quando a memória está cheia, a primeira página carregada é removida.
LRU: Usa um LinkedHashMap com ordem de acesso para rastrear a ordem de uso das páginas, facilitando a localização da página menos recentemente usada.
Ótimo: Usa uma abordagem que prevê o uso futuro das páginas. A página que não será usada por mais tempo é removida da memória.
Relógio: Implementa uma técnica semelhante ao FIFO, mas com um ponteiro circular e bits de uso para indicar se uma página foi acessada recentemente.
Como Usar

1. Compile e execute o programa.
   ```
   No terminal, vá ao diretorio do programa
   javac PageReplacementSimulator.java
   java PageReplacementSimulator
   ```
3. Insira a sequência de páginas, separadas por espaço.
4. Insira o número de quadros de memória.
5. O programa exibirá o número de faltas de página para cada método.
