package votecounterproject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @version Spring 2019
 * @author clatulip
 * Modified for Fall 2022 by Bruce Long
 * @since 08-29-2022
 */
public class ArrayListVoteCounter {

    private ArrayList<String> votes;
    private ArrayList<String> spoiledVotes;
    private SithSenateMember[] sithSenateMembers;

    public ArrayListVoteCounter() {
        votes = new ArrayList<>();
        spoiledVotes = new ArrayList<>();
        sithSenateMembers = new SithSenateMember[4];
        setupCandidates();
    }

    private void setupCandidates() {
        sithSenateMembers[0] = new SithSenateMember("Sidius");
        sithSenateMembers[1] = new SithSenateMember("Maul");
        sithSenateMembers[2] = new SithSenateMember("Vader");
        sithSenateMembers[3] = new SithSenateMember("Plagueis");
    }

    public void recordVote(String name) {
        if (!name.isEmpty()) {
            votes.add(name);
            switch (name) {
                case "Darth Sidius":
                    sithSenateMembers[0].addVote();
                    break;
                case "Darth Maul":
                    sithSenateMembers[1].addVote();
                    break;
                case "Darth Vader":
                    sithSenateMembers[2].addVote();
                    break;
                case "Darth Plagueis":
                    sithSenateMembers[3].addVote();
                    break;
                default:
                    spoiledVotes.add(name);
            }
        }
    }

    public void reportResults() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        int validVotes = votes.size() - spoiledVotes.size();
        System.out.println(validVotes + " valid votes were cast.");
        for (SithSenateMember sithSenateMember : sithSenateMembers) {
            System.out.println("Darth " + sithSenateMember.getSurname() + ": " 
                    + getSithSenateMemberVotes(sithSenateMember.getFullName())
                    + " votes, " + df.format(sithSenateMember.getNumVotes() / 
                            (float) validVotes * 100) + "%.");
        }
        System.out.println("There were " + spoiledVotes.size() + 
                " spoiled votes.");
    }

    public int getSithSenateMemberVotes(String name) {
        for (int i = 0; i < sithSenateMembers.length; i++) {
            if (sithSenateMembers[i].getFullName().equalsIgnoreCase(name) ||
                sithSenateMembers[i].getSurname().equalsIgnoreCase(name)) {
                return sithSenateMembers[i].getNumVotes();
            }
        }
        return 0;
    }

    public ArrayList<String> getVotes() {
        return votes;
    }

    public ArrayList<String> getSpoiledVotes() {
        return spoiledVotes;
    }

    public static void runRandomElectionResults() {
        ArrayListVoteCounter voteCounter = new ArrayListVoteCounter();
        voteCounter.setupCandidates();
        voteCounter.generateRandomElectionData();
        voteCounter.reportResults();
    }

    private void generateRandomElectionData() {
        int n;
        int ballotCount = ThreadLocalRandom.current().nextInt(99999);
        for (int i = 0; i < ballotCount; i++) {
            n = ThreadLocalRandom.current().nextInt(0, 100);
            if (0 < n && n < 25) {
                recordVote(sithSenateMembers[0].getFullName());
            } else if (25 < n && n < 45) {
                recordVote(sithSenateMembers[1].getFullName());
            } else if (45 < n && n < 74) {
                recordVote(sithSenateMembers[2].getFullName());
            } else if (74 < n && n < 95) {
                recordVote(sithSenateMembers[3].getFullName());
            } else {
                recordVote("unknown");
            }
        }
    }
}
