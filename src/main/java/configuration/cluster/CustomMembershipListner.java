package configuration.cluster;

import com.hazelcast.core.*;
import com.hazelcast.nio.Address;

import java.util.Set;

/**
 * Created by bijoy on 18/6/16.
 */
public class CustomMembershipListner implements MembershipListener {
    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        System.out.println("#####################"+ membershipEvent);
        membersString(membershipEvent.getCluster());
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        System.out.println("#####################"+ membershipEvent);
        membersString(membershipEvent.getCluster());
    }

    @Override
    public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
        System.out.println("##################### Member "+ memberAttributeEvent.getMember().getAddress()+", attribute "+memberAttributeEvent.getValue());
        membersString(memberAttributeEvent.getCluster());
    }

    private void membersString(Cluster cluster){
        Set<Member> members = cluster.getMembers();
        Member localMember = cluster.getLocalMember();
        Address address;

        System.out.print("Members [" + members.size() + "] {");
        for (Member member : members) {
            address = member.getAddress();
            System.out.print("\n {"+member.getStringAttribute("user")+" -> Instance "+address.getPort() % 10+"} ");
            System.out.print("[" + address.getHost() + "]:" + address.getPort());
            if(localMember.getAddress().equals(address)) {
                System.out.print(" this");
            }
        }
        System.out.println("\n}");
    }
}
