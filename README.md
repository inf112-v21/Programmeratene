![alt text](https://raw.githubusercontent.com/inf112-v21/Programmeratene/master/assets/other/logo_programmeratene_inf112v21.png)

# INF112 Gruppe 10 - Lag 4 Programmeratene
Uni project where we have to make a RoboRally game with libgdx. This game supports up to 8 players on a client / host based system. We use Kryonet for all our multiplayer features. Read more about Kryonet [here](https://github.com/EsotericSoftware/kryonet) <br/>


[![Build Status](https://travis-ci.com/inf112-v21/Programmeratene.svg?branch=master)](https://travis-ci.com/inf112-v21/Programmeratene)

## Members
- Mathias Naess Iversen (mathiasni) - TEAM LEAD
- Jakob Slyngstadli (jakobdengode) - PROJECT MANAGER
- Jan Erik Syltøy (jeriks31) - TECH LEAD
- Simen Sørensen (sisorensen) - PRODUCT OWNER
- Erland Myklebust (emy004) - SUBJECT MATTER EXPERT

## Requirements
- Java (tested on jdk 13 and 15)

## How to run game
1. Clone repository from https://github.com/inf112-v21/Programmeratene
2. Open repository in your IDE as a Maven project
3. Run Main.main()

## How to play
1. See "How to run game"
2. Choose between Host or Player
3. If Host, wait for Clients to connect / If Client, enter Host IP to connect to Host \
   3.1 If there are more than 2 players, host types "y" \
   3.2 When everyone has connected, host types "n"
4. Pick cards from the terminal with card numbers 1-9
5. Pick your cards in preferred order, afterwards the robots will move in order after card priority (the highest first)
6. After every robot has done all movements for that round, we get a new round with new cards   
7. The first one to reach the flag wins. Be aware of holes and other dangers!

### "Enter Host IP? What's that?"
#### If playing locally:
1. Host can open a command prompt and type 'ipconfig'. 
2. Look for the numbers labeled 'IPv4'
#### If playing on different networks:
1. The host must port-forward port '27960' (Look up a guide specific to your router!)
2. Find your public IP on [WhatsMyIP](https://www.whatsmyip.org/)

## How to run tests
1. Clone repository from https://github.com/inf112-v21/Programmeratene
2. Open repository in your IDE as a Maven project
3. Run directly from test folder


## How to run manual tests
1. Open Project folder. 
2. Open Documents folder
3. Open Manuelle Tester folder
4. Open ManuelleTester.pdf
5. Follow the instructions for each test.

## Rules
Read more about how to play RoboRally [here](https://www.fgbradleys.com/rules/rules4/Robo%20Rally%20-%20rules.pdf)
