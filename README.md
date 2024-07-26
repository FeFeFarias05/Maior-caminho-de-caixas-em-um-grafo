Este projeto contém o arquivo Box.java no qual contém todo a implementação do projeto, o app.java que chama os métodos de Box, a In.java e 12 arquivos .txt para teste, no qual cada um contém um grafo com quantidades diferentes de vértices.
O projeto foi feito baseado no seguinte contexto:
Cada vértice representa uma caixa que possui 3 dimensões, no qual não se sabe qual a ordem que cada valor representa, ou seja, não se sabe se a primeira medida é a altura, comprimento ou largura e assim vice-versa. O objetivo desse projeto é achar a maior sequência de caixas que podem ser colocadas uma dentro da outra, fornecendo como informação final o comprimento da sequência mais longa de caixas que cabem uma dentro da outra.
*IMplementação*
Para implementação foi criada uma função que pegava o valor de cada vértice que estava em apenas uma string e dividia colocando em uma lista já convertendo para inteiro e colocando em ordem. 

int[] getMeasures(String measures) {
    String[] separatedMeasures = measures.split(" ");
    int[] measuresNum = new int[separatedMeasures.length];

    for (int i = 0; i < separatedMeasures.length; i++) {
        measuresNum[i] = Integer.parseInt(separatedMeasures[i]);
    }

    Arrays.sort(measuresNum);
    return measuresNum;
}

Para realizar a comparação, foi utilizada a seguinte lógica: como não se conhece o significado exato de cada valor, se qualquer valor do vértice que está sendo comparado for maior do que qualquer valor do próximo vértice, já se pode concluir que ele não caberá dentro da caixa a ser comparada.
A implementação desse lógica foi feita diretamente no contrutor da classe Box

public Box(String filename) {
    this();
    try {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        Map<String, int[]> lineMeasures = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                int[] measures = getMeasures(line);
                lineMeasures.put(line, measures);
            }
        }

        for (Map.Entry<String, int[]> boxMeasuresOne : lineMeasures.entrySet()) {
            for (Map.Entry<String, int[]> boxMesuresTwo : lineMeasures.entrySet()) {
                if (!boxMeasuresOne.getKey().equals(boxMesuresTwo.getKey())) {
                    boolean result = true;
                    for (int k = 0; k < boxMeasuresOne.getValue().length; k++) {
                        if (boxMeasuresOne.getValue()[k] >= boxMesuresTwo.getValue()[k]) {
                            result = false;
                            break;
                        }
                    }
                    if (result) {
                        addEdge(boxMeasuresOne.getKey(), boxMesuresTwo.getKey()); // Adiciona a aresta no grafo
                    }
                }
            }
        }

        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}

Como o projeto pedia para encontrar a maior sequência de caixa, fiz um caminhamento baseado no DFS em que enquanto caminhava, já analisava qual era o maior caminho. O algoritmo é recursivo e por tanto tem o público e o private.

  public int biggerPath() {
    int biggerPath = 0;
    Map<String, Integer> length = new HashMap<>(); 
    for (String vertex : getVerts()) {
        HashSet<String> visited = new HashSet<>();
        int pathLength = findLargestPath(vertex, visited, length);
        biggerPath = Math.max(biggerPath, pathLength);
    }
    return biggerPath ; 
}

  private int findLargestPath(String vertex, Set<String> visited, Map<String, Integer> length) {
      if (length.containsKey(vertex)) {
          return length.get(vertex); 
      }
      visited.add(vertex);
      int pathLength = 1; 

      for (String adjacent : getAdj(vertex)) {
          if (!visited.contains(adjacent)) {
              visited.add(adjacent); 
              pathLength = Math.max(pathLength, 1 + findLargestPath(adjacent, visited, length));
              visited.remove(adjacent); 
          }
      }

      length.put(vertex, pathLength); 
      return pathLength;
  }

Para conseguir ter uma visualização melhor do grafo construído, utilizei a função toDot que gera um algoritmo, no qual é colado no site Graphviz Online que constroí uma grafo.
