public class GroupsNode {
    Object leftValue;
    Object rightValue;
    Token op;
    int raiting ;
    //rating будет зависить от количества скобок, чтобы понять, в каком порядке должно считаться. За наличие скобок прибавляется +2 к рейтингу, за наличие знака типа */ ,
    //которые математично должны
    public GroupsNode(Object leftValue, Object rightValue, Token op, int raiting) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.op = op;
        this.raiting = raiting;
    }

}