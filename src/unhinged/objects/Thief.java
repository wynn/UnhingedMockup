package unhinged.objects;

public class Thief extends Player {

	//CONSTRUCTOR
			public Thief(){
				
				className = "Thief";
				
				baseHealth = 4;
				baseSanity = 0;

				baseAttack = 2;
				baseSpeed = 4;
				
				baseCD1 = 6;
				baseCD2 = 6;
				
				baseTimeout1 = 4;
				baseTimeout2 = 4;
				baseTimeout3 = 4;
				
				
				health = baseHealth;
				sanity = baseSanity;
				
				attack = baseAttack;
				speed = baseSpeed;
				
				currHealth = baseHealth;
				currSanity = baseSanity;

				currAttack = baseAttack;
				currSpeed = baseSpeed;
			
				CD1 = 0;
				CD2 = 0;
				CD3 = 0;
				
				Timeout1 = 0;
				Timeout2 = 0;
				Timeout3 = 0;
				
				riftStones = 0;
				
				usedPortal = false;
				isVisible = true;
				this.setName("Player" + " (" + this.className + ") " + playerNumber);
			}
				
			
			
			//Class Abilities
			////////////////////////////////////////////////////////////
			// ABILITY 1, cooldown is 'CD1'
			void hide(){
				
				isVisible = false;
				//sets is visible equal to false, at beggining of player turn all
				//thing will go to true
				
			}// END Heal
			
			////////////////////////////////////////////////////////////
			// ABILITY 2, cooldown is 'CD2'
			void scout(){
			
				//Involves the map
				
				CD2 = baseCD2;
			}// END cure
			
			////////////////////////////////////////////////////////////
			// Ability 3, cooldown is 'CD3'
			void  examine(){
				
				//Involves the map
				
				CD3 = baseCD3;
			}//END Revive
			////////////////////////////////////////////////////////////

			@Override
			public void cleanUp(){ //RUNS AT END OF TURN, decrements CD's
				////////////////////////////////////////////////////////
				//Ability 1
				if(CD1>0){
					CD1 = CD1 - 1;
				}
				
				
				////////////////////////////////////////////////////////
				// Ability 2
				if(CD2>0){
					CD2 = CD2 - 1;
				}
				////////////////////////////////////////////////////////
				// Ability 3
				if(CD3>0){
					CD3 = CD3-1;
				}

				
			}// End CleanUp

	}
