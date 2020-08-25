package main
import (
	"fmt"
)

func gameOfLife(board [][]int)  {
    fmt.Println("**** Game of Life ****")
    const (
		aliveToDead = -1
        alive = 1
        deadToAlive = 2
    )
    
    for i := 0; i<len(board); i++ {
        for j := 0; j<len(board[i]); j++ {
            
            var (
                numAlive int
                isAlive = alive == board[i][j]
            )
            
            for _, d := range [][]int{{0, -1}, {-1, -1}, {-1, 1}, {-1, 0}, {0, 1}, {1, 0}, {1, 1}, {1, -1}} {
                x, y := i+d[0], j+d[1]
                if x < 0 || x >= len(board) || y < 0 || y >= len(board[i]) {
                    continue
                }
                if alive == board[x][y] || aliveToDead == board[x][y] {
                    numAlive++
                }
            }
            
            switch {
                case isAlive && (numAlive < 2 || numAlive > 3):
                    board[i][j] = aliveToDead
                case false == isAlive && 3 == numAlive:
                    board[i][j] = deadToAlive
            }
        }   
    }
    
    
    for i := 0; i<len(board); i++ {
        for j := 0; j<len(board[i]); j++ {
            if aliveToDead == board[i][j] {
                board[i][j] = 0
           
            } else if deadToAlive == board[i][j] {
                board[i][j] = 1   
                     
            }
        }
    }
    fmt.Println(board)
}
func main() {
    bo := [][]int{
          {0,1,0},
          {0,0,1},
          {1,1,1},
          {0,0,0},
    }
    gameOfLife(bo)
}

/* input 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
*/
/* Expected output
 * [
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
] 
*/
