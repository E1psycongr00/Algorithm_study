import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Solution {

    MemberTable table = new MemberTable();
    Service service = new Service(table);

    public int[] solution(String[] id_list, String[] report, int k) {
        for (String s : id_list) {
            table.create(s);
        }

        for (String r : report) {
            String[] s = r.split(" ");
            service.reportOther(s[0], s[1], k);
        }

        return Arrays.stream(id_list).map(id -> {
            Member member = table.find(id);
            return member.reportList.stream().filter(m -> m.reported >= k)
                .count();
        }).mapToInt(Long::intValue).toArray();
    }


}


class Member {

    public final String id;
    public int report = 0;
    public int reported = 0;
    public HashSet<Member> reportBy = new HashSet<>();
    public HashSet<Member> reportList = new HashSet<>();

    public Member(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member{" +
            "id='" + id + '\'' +
            ", report=" + report +
            ", reported=" + reported +
            '}';
    }
}

class MemberTable {

    private final HashMap<String, Member> table = new HashMap<>();

    public Member find(String id) {
        return table.get(id);
    }

    public Member create(String id) {
        return table.put(id, new Member(id));
    }

    public boolean update(Member member) {
        table.put(member.id, member);
        return true;
    }

    @Override
    public String toString() {
        return "MemberTable{" +
            "table=" + table +
            '}';
    }
}

class Service {

    private MemberTable memberTable;

    public Service(MemberTable memberTable) {
        this.memberTable = memberTable;
    }

    public void reportOther(String id1, String id2, int k) {
        Member member1 = memberTable.find(id1);
        Member member2 = memberTable.find(id2);

        member1.reportList.add(member2);

        member2.reportBy.add(member1);

        member1.report = member1.reportList.size();
        member2.reported = member2.reportBy.size();

        memberTable.update(member1);
        memberTable.update(member2);
    }

}